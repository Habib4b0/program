package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemGroupDetailsLocalService}.
 *
 * @author
 * @see ItemGroupDetailsLocalService
 * @generated
 */
public class ItemGroupDetailsLocalServiceWrapper
    implements ItemGroupDetailsLocalService,
        ServiceWrapper<ItemGroupDetailsLocalService> {
    private ItemGroupDetailsLocalService _itemGroupDetailsLocalService;

    public ItemGroupDetailsLocalServiceWrapper(
        ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
        _itemGroupDetailsLocalService = itemGroupDetailsLocalService;
    }

    /**
    * Adds the item group details to the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupDetails the item group details
    * @return the item group details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails addItemGroupDetails(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.addItemGroupDetails(itemGroupDetails);
    }

    /**
    * Creates a new item group details with the primary key. Does not add the item group details to the database.
    *
    * @param itemGroupDetailsSid the primary key for the new item group details
    * @return the new item group details
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails createItemGroupDetails(
        int itemGroupDetailsSid) {
        return _itemGroupDetailsLocalService.createItemGroupDetails(itemGroupDetailsSid);
    }

    /**
    * Deletes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupDetailsSid the primary key of the item group details
    * @return the item group details that was removed
    * @throws PortalException if a item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails deleteItemGroupDetails(
        int itemGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.deleteItemGroupDetails(itemGroupDetailsSid);
    }

    /**
    * Deletes the item group details from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupDetails the item group details
    * @return the item group details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails deleteItemGroupDetails(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.deleteItemGroupDetails(itemGroupDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemGroupDetailsLocalService.dynamicQuery();
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
        return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemGroupDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _itemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemGroupDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemGroupDetails fetchItemGroupDetails(
        int itemGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.fetchItemGroupDetails(itemGroupDetailsSid);
    }

    /**
    * Returns the item group details with the primary key.
    *
    * @param itemGroupDetailsSid the primary key of the item group details
    * @return the item group details
    * @throws PortalException if a item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails getItemGroupDetails(
        int itemGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.getItemGroupDetails(itemGroupDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item group detailses
    * @param end the upper bound of the range of item group detailses (not inclusive)
    * @return the range of item group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemGroupDetails> getItemGroupDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.getItemGroupDetailses(start, end);
    }

    /**
    * Returns the number of item group detailses.
    *
    * @return the number of item group detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemGroupDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.getItemGroupDetailsesCount();
    }

    /**
    * Updates the item group details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemGroupDetails the item group details
    * @return the item group details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemGroupDetails updateItemGroupDetails(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemGroupDetailsLocalService.updateItemGroupDetails(itemGroupDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemGroupDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemGroupDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemGroupDetailsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List getProductGroups(java.lang.String productGroupNo,
        java.lang.String productGroupName, java.lang.String company,
        java.lang.String segment, int startIndex, int offset,
        java.util.Set<com.vaadin.v7.data.Container.Filter> filters,
        java.util.List<org.asi.ui.extfilteringtable.paged.logic.SortByColumn> sortByColumns) {
        return _itemGroupDetailsLocalService.getProductGroups(productGroupNo,
            productGroupName, company, segment, startIndex, offset, filters,
            sortByColumns);
    }

    @Override
    public java.util.List getItemMasterRecords(java.lang.String[] query) {
        return _itemGroupDetailsLocalService.getItemMasterRecords(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemGroupDetailsLocalService getWrappedItemGroupDetailsLocalService() {
        return _itemGroupDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemGroupDetailsLocalService(
        ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
        _itemGroupDetailsLocalService = itemGroupDetailsLocalService;
    }

    @Override
    public ItemGroupDetailsLocalService getWrappedService() {
        return _itemGroupDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        ItemGroupDetailsLocalService itemGroupDetailsLocalService) {
        _itemGroupDetailsLocalService = itemGroupDetailsLocalService;
    }
}
