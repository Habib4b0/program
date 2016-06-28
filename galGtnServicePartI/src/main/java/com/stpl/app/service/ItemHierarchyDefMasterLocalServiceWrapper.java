package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemHierarchyDefMasterLocalService}.
 *
 * @author
 * @see ItemHierarchyDefMasterLocalService
 * @generated
 */
public class ItemHierarchyDefMasterLocalServiceWrapper
    implements ItemHierarchyDefMasterLocalService,
        ServiceWrapper<ItemHierarchyDefMasterLocalService> {
    private ItemHierarchyDefMasterLocalService _itemHierarchyDefMasterLocalService;

    public ItemHierarchyDefMasterLocalServiceWrapper(
        ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
        _itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
    }

    /**
    * Adds the item hierarchy def master to the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMaster the item hierarchy def master
    * @return the item hierarchy def master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster addItemHierarchyDefMaster(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.addItemHierarchyDefMaster(itemHierarchyDefMaster);
    }

    /**
    * Creates a new item hierarchy def master with the primary key. Does not add the item hierarchy def master to the database.
    *
    * @param itemHierarchyDefMasterSid the primary key for the new item hierarchy def master
    * @return the new item hierarchy def master
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster createItemHierarchyDefMaster(
        int itemHierarchyDefMasterSid) {
        return _itemHierarchyDefMasterLocalService.createItemHierarchyDefMaster(itemHierarchyDefMasterSid);
    }

    /**
    * Deletes the item hierarchy def master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master that was removed
    * @throws PortalException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster deleteItemHierarchyDefMaster(
        int itemHierarchyDefMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.deleteItemHierarchyDefMaster(itemHierarchyDefMasterSid);
    }

    /**
    * Deletes the item hierarchy def master from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMaster the item hierarchy def master
    * @return the item hierarchy def master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster deleteItemHierarchyDefMaster(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.deleteItemHierarchyDefMaster(itemHierarchyDefMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemHierarchyDefMasterLocalService.dynamicQuery();
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
        return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemHierarchyDefMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _itemHierarchyDefMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemHierarchyDefMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster fetchItemHierarchyDefMaster(
        int itemHierarchyDefMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.fetchItemHierarchyDefMaster(itemHierarchyDefMasterSid);
    }

    /**
    * Returns the item hierarchy def master with the primary key.
    *
    * @param itemHierarchyDefMasterSid the primary key of the item hierarchy def master
    * @return the item hierarchy def master
    * @throws PortalException if a item hierarchy def master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster getItemHierarchyDefMaster(
        int itemHierarchyDefMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMaster(itemHierarchyDefMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item hierarchy def masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyDefMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy def masters
    * @param end the upper bound of the range of item hierarchy def masters (not inclusive)
    * @return the range of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemHierarchyDefMaster> getItemHierarchyDefMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMasters(start,
            end);
    }

    /**
    * Returns the number of item hierarchy def masters.
    *
    * @return the number of item hierarchy def masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemHierarchyDefMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.getItemHierarchyDefMastersCount();
    }

    /**
    * Updates the item hierarchy def master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyDefMaster the item hierarchy def master
    * @return the item hierarchy def master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyDefMaster updateItemHierarchyDefMaster(
        com.stpl.app.model.ItemHierarchyDefMaster itemHierarchyDefMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyDefMasterLocalService.updateItemHierarchyDefMaster(itemHierarchyDefMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemHierarchyDefMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemHierarchyDefMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemHierarchyDefMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemHierarchyDefMasterLocalService getWrappedItemHierarchyDefMasterLocalService() {
        return _itemHierarchyDefMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemHierarchyDefMasterLocalService(
        ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
        _itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
    }

    @Override
    public ItemHierarchyDefMasterLocalService getWrappedService() {
        return _itemHierarchyDefMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ItemHierarchyDefMasterLocalService itemHierarchyDefMasterLocalService) {
        _itemHierarchyDefMasterLocalService = itemHierarchyDefMasterLocalService;
    }
}
