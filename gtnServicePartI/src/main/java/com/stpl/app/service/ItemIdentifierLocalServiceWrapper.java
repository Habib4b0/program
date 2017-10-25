package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ItemIdentifierLocalService}.
 *
 * @author
 * @see ItemIdentifierLocalService
 * @generated
 */
public class ItemIdentifierLocalServiceWrapper
    implements ItemIdentifierLocalService,
        ServiceWrapper<ItemIdentifierLocalService> {
    private ItemIdentifierLocalService _itemIdentifierLocalService;

    public ItemIdentifierLocalServiceWrapper(
        ItemIdentifierLocalService itemIdentifierLocalService) {
        _itemIdentifierLocalService = itemIdentifierLocalService;
    }

    /**
    * Adds the item identifier to the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifier the item identifier
    * @return the item identifier that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemIdentifier addItemIdentifier(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.addItemIdentifier(itemIdentifier);
    }

    /**
    * Creates a new item identifier with the primary key. Does not add the item identifier to the database.
    *
    * @param itemIdentifierSid the primary key for the new item identifier
    * @return the new item identifier
    */
    @Override
    public com.stpl.app.model.ItemIdentifier createItemIdentifier(
        int itemIdentifierSid) {
        return _itemIdentifierLocalService.createItemIdentifier(itemIdentifierSid);
    }

    /**
    * Deletes the item identifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier that was removed
    * @throws PortalException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemIdentifier deleteItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.deleteItemIdentifier(itemIdentifierSid);
    }

    /**
    * Deletes the item identifier from the database. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifier the item identifier
    * @return the item identifier that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemIdentifier deleteItemIdentifier(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.deleteItemIdentifier(itemIdentifier);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _itemIdentifierLocalService.dynamicQuery();
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
        return _itemIdentifierLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemIdentifierLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _itemIdentifierLocalService.dynamicQuery(dynamicQuery, start,
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
        return _itemIdentifierLocalService.dynamicQueryCount(dynamicQuery);
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
        return _itemIdentifierLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ItemIdentifier fetchItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.fetchItemIdentifier(itemIdentifierSid);
    }

    /**
    * Returns the item identifier with the primary key.
    *
    * @param itemIdentifierSid the primary key of the item identifier
    * @return the item identifier
    * @throws PortalException if a item identifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemIdentifier getItemIdentifier(
        int itemIdentifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.getItemIdentifier(itemIdentifierSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item identifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item identifiers
    * @param end the upper bound of the range of item identifiers (not inclusive)
    * @return the range of item identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ItemIdentifier> getItemIdentifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.getItemIdentifiers(start, end);
    }

    /**
    * Returns the number of item identifiers.
    *
    * @return the number of item identifiers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getItemIdentifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.getItemIdentifiersCount();
    }

    /**
    * Updates the item identifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemIdentifier the item identifier
    * @return the item identifier that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ItemIdentifier updateItemIdentifier(
        com.stpl.app.model.ItemIdentifier itemIdentifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.updateItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _itemIdentifierLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _itemIdentifierLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _itemIdentifierLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.stpl.app.model.ItemIdentifier> findByItemIrtIdentifierDetails(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _itemIdentifierLocalService.findByItemIrtIdentifierDetails(itemMasterSid);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ItemIdentifierLocalService getWrappedItemIdentifierLocalService() {
        return _itemIdentifierLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedItemIdentifierLocalService(
        ItemIdentifierLocalService itemIdentifierLocalService) {
        _itemIdentifierLocalService = itemIdentifierLocalService;
    }

    @Override
    public ItemIdentifierLocalService getWrappedService() {
        return _itemIdentifierLocalService;
    }

    @Override
    public void setWrappedService(
        ItemIdentifierLocalService itemIdentifierLocalService) {
        _itemIdentifierLocalService = itemIdentifierLocalService;
    }
}
