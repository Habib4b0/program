package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemGroupLocalService}.
 *
 * @author
 * @see ItemGroupLocalService
 * @generated
 */
public class ItemGroupLocalServiceWrapper implements ItemGroupLocalService,
    ServiceWrapper<ItemGroupLocalService> {
    private ItemGroupLocalService _itemGroupLocalService;

    public ItemGroupLocalServiceWrapper(
        ItemGroupLocalService itemGroupLocalService) {
        _itemGroupLocalService = itemGroupLocalService;
    }

    /**
    * Adds the item group to the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroup addItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.addItemGroup(itemGroup);
    }

    /**
    * Creates a new item group with the primary key. Does not add the item group to the database.
    *
    * @param itemGroupSid the primary key for the new item group
    * @return the new item group
    */
    @Override
    public com.stpl.app.model.ItemGroup createItemGroup(int itemGroupSid) {
        return _itemGroupLocalService.createItemGroup(itemGroupSid);
    }

    /**
    * Deletes the item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group that was removed
    * @throws PortalException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroup deleteItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.deleteItemGroup(itemGroupSid);
    }

    /**
    * Deletes the item group from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroup deleteItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.deleteItemGroup(itemGroup);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemGroupLocalService.dynamicQuery();
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
        return _itemGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemGroupLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemGroupLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _itemGroupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemGroupLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.stpl.app.model.ItemGroup fetchItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.fetchItemGroup(itemGroupSid);
    }

    /**
    * Returns the item group with the primary key.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group
    * @throws PortalException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroup getItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.getItemGroup(itemGroupSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item groups
    * @param end the upper bound of the range of item groups (not inclusive)
    * @return the range of item groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemGroup> getItemGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.getItemGroups(start, end);
    }

    /**
    * Returns the number of item groups.
    *
    * @return the number of item groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.getItemGroupsCount();
    }

    /**
    * Updates the item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroup updateItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupLocalService.updateItemGroup(itemGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemGroupLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getItemGroupMaster(java.lang.String itemGroupName) {
        return _itemGroupLocalService.getItemGroupMaster(itemGroupName);
    }

    @Override
    public java.util.List getItemGroupDetails(java.lang.String itemType,
        java.lang.String itemDesc, java.lang.String brand,
        java.lang.String strength, java.lang.String itemNoCriteria,
        java.lang.String therapeuticCriteria, java.lang.String formCriteria) {
        return _itemGroupLocalService.getItemGroupDetails(itemType, itemDesc,
            brand, strength, itemNoCriteria, therapeuticCriteria, formCriteria);
    }

    @Override
    public java.util.List getAvailableSearchResults(
        java.lang.String finalCriteria) {
        return _itemGroupLocalService.getAvailableSearchResults(finalCriteria);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemGroupLocalService getWrappedItemGroupLocalService() {
        return _itemGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemGroupLocalService(
        ItemGroupLocalService itemGroupLocalService) {
        _itemGroupLocalService = itemGroupLocalService;
    }

    @Override
    public ItemGroupLocalService getWrappedService() {
        return _itemGroupLocalService;
    }

    @Override
    public void setWrappedService(ItemGroupLocalService itemGroupLocalService) {
        _itemGroupLocalService = itemGroupLocalService;
    }
}
