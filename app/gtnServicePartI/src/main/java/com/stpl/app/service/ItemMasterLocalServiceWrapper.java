package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemMasterLocalService}.
 *
 * @author
 * @see ItemMasterLocalService
 * @generated
 */
public class ItemMasterLocalServiceWrapper implements ItemMasterLocalService,
    ServiceWrapper<ItemMasterLocalService> {
    private ItemMasterLocalService _itemMasterLocalService;

    public ItemMasterLocalServiceWrapper(
        ItemMasterLocalService itemMasterLocalService) {
        _itemMasterLocalService = itemMasterLocalService;
    }

    /**
    * Adds the item master to the database. Also notifies the appropriate model listeners.
    *
    * @param itemMaster the item master
    * @return the item master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemMaster addItemMaster(
        com.stpl.app.model.ItemMaster itemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.addItemMaster(itemMaster);
    }

    /**
    * Creates a new item master with the primary key. Does not add the item master to the database.
    *
    * @param itemMasterSid the primary key for the new item master
    * @return the new item master
    */
    @Override
    public com.stpl.app.model.ItemMaster createItemMaster(int itemMasterSid) {
        return _itemMasterLocalService.createItemMaster(itemMasterSid);
    }

    /**
    * Deletes the item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the item master
    * @return the item master that was removed
    * @throws PortalException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemMaster deleteItemMaster(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.deleteItemMaster(itemMasterSid);
    }

    /**
    * Deletes the item master from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMaster the item master
    * @return the item master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemMaster deleteItemMaster(
        com.stpl.app.model.ItemMaster itemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.deleteItemMaster(itemMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemMasterLocalService.dynamicQuery();
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
        return _itemMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _itemMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemMaster fetchItemMaster(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.fetchItemMaster(itemMasterSid);
    }

    /**
    * Returns the item master with the primary key.
    *
    * @param itemMasterSid the primary key of the item master
    * @return the item master
    * @throws PortalException if a item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemMaster getItemMaster(int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.getItemMaster(itemMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item masters
    * @param end the upper bound of the range of item masters (not inclusive)
    * @return the range of item masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemMaster> getItemMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.getItemMasters(start, end);
    }

    /**
    * Returns the number of item masters.
    *
    * @return the number of item masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.getItemMastersCount();
    }

    /**
    * Updates the item master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemMaster the item master
    * @return the item master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemMaster updateItemMaster(
        com.stpl.app.model.ItemMaster itemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemMasterLocalService.updateItemMaster(itemMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List findItemMaster(java.lang.String itemId,
        java.lang.String itemNo, java.lang.String itemName,
        java.lang.String itemStatus, java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String manufacturerId,
        int identifierType, java.lang.String identifier,
        java.lang.String brand, java.lang.String orderByColumn,
        java.lang.Boolean sortOrder,
        java.util.Map<java.lang.String, java.lang.Object> parameters) {
        return _itemMasterLocalService.findItemMaster(itemId, itemNo, itemName,
            itemStatus, itemType, itemDesc, manufacturerId, identifierType,
            identifier, brand, orderByColumn, sortOrder, parameters);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemMasterLocalService getWrappedItemMasterLocalService() {
        return _itemMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemMasterLocalService(
        ItemMasterLocalService itemMasterLocalService) {
        _itemMasterLocalService = itemMasterLocalService;
    }

    @Override
    public ItemMasterLocalService getWrappedService() {
        return _itemMasterLocalService;
    }

    @Override
    public void setWrappedService(ItemMasterLocalService itemMasterLocalService) {
        _itemMasterLocalService = itemMasterLocalService;
    }
}
