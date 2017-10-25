package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemHierarchyMasterLocalService}.
 *
 * @author
 * @see ItemHierarchyMasterLocalService
 * @generated
 */
public class ItemHierarchyMasterLocalServiceWrapper
    implements ItemHierarchyMasterLocalService,
        ServiceWrapper<ItemHierarchyMasterLocalService> {
    private ItemHierarchyMasterLocalService _itemHierarchyMasterLocalService;

    public ItemHierarchyMasterLocalServiceWrapper(
        ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
        _itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
    }

    /**
    * Adds the item hierarchy master to the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyMaster the item hierarchy master
    * @return the item hierarchy master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster addItemHierarchyMaster(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.addItemHierarchyMaster(itemHierarchyMaster);
    }

    /**
    * Creates a new item hierarchy master with the primary key. Does not add the item hierarchy master to the database.
    *
    * @param itemHierarchyMasterSid the primary key for the new item hierarchy master
    * @return the new item hierarchy master
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster createItemHierarchyMaster(
        int itemHierarchyMasterSid) {
        return _itemHierarchyMasterLocalService.createItemHierarchyMaster(itemHierarchyMasterSid);
    }

    /**
    * Deletes the item hierarchy master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyMasterSid the primary key of the item hierarchy master
    * @return the item hierarchy master that was removed
    * @throws PortalException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster deleteItemHierarchyMaster(
        int itemHierarchyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.deleteItemHierarchyMaster(itemHierarchyMasterSid);
    }

    /**
    * Deletes the item hierarchy master from the database. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyMaster the item hierarchy master
    * @return the item hierarchy master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster deleteItemHierarchyMaster(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.deleteItemHierarchyMaster(itemHierarchyMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemHierarchyMasterLocalService.dynamicQuery();
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
        return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemHierarchyMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _itemHierarchyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemHierarchyMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemHierarchyMaster fetchItemHierarchyMaster(
        int itemHierarchyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.fetchItemHierarchyMaster(itemHierarchyMasterSid);
    }

    /**
    * Returns the item hierarchy master with the primary key.
    *
    * @param itemHierarchyMasterSid the primary key of the item hierarchy master
    * @return the item hierarchy master
    * @throws PortalException if a item hierarchy master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster getItemHierarchyMaster(
        int itemHierarchyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.getItemHierarchyMaster(itemHierarchyMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item hierarchy masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemHierarchyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item hierarchy masters
    * @param end the upper bound of the range of item hierarchy masters (not inclusive)
    * @return the range of item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemHierarchyMaster> getItemHierarchyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.getItemHierarchyMasters(start,
            end);
    }

    /**
    * Returns the number of item hierarchy masters.
    *
    * @return the number of item hierarchy masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemHierarchyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.getItemHierarchyMastersCount();
    }

    /**
    * Updates the item hierarchy master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemHierarchyMaster the item hierarchy master
    * @return the item hierarchy master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemHierarchyMaster updateItemHierarchyMaster(
        com.stpl.app.model.ItemHierarchyMaster itemHierarchyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemHierarchyMasterLocalService.updateItemHierarchyMaster(itemHierarchyMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemHierarchyMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemHierarchyMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemHierarchyMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemHierarchyMasterLocalService getWrappedItemHierarchyMasterLocalService() {
        return _itemHierarchyMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemHierarchyMasterLocalService(
        ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
        _itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
    }

    @Override
    public ItemHierarchyMasterLocalService getWrappedService() {
        return _itemHierarchyMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ItemHierarchyMasterLocalService itemHierarchyMasterLocalService) {
        _itemHierarchyMasterLocalService = itemHierarchyMasterLocalService;
    }
}
