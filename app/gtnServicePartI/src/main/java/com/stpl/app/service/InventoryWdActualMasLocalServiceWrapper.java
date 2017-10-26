package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InventoryWdActualMasLocalService}.
 *
 * @author
 * @see InventoryWdActualMasLocalService
 * @generated
 */
public class InventoryWdActualMasLocalServiceWrapper
    implements InventoryWdActualMasLocalService,
        ServiceWrapper<InventoryWdActualMasLocalService> {
    private InventoryWdActualMasLocalService _inventoryWdActualMasLocalService;

    public InventoryWdActualMasLocalServiceWrapper(
        InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
        _inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
    }

    /**
    * Adds the inventory wd actual mas to the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualMas the inventory wd actual mas
    * @return the inventory wd actual mas that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas addInventoryWdActualMas(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.addInventoryWdActualMas(inventoryWdActualMas);
    }

    /**
    * Creates a new inventory wd actual mas with the primary key. Does not add the inventory wd actual mas to the database.
    *
    * @param inventoryWdActualMasSid the primary key for the new inventory wd actual mas
    * @return the new inventory wd actual mas
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas createInventoryWdActualMas(
        int inventoryWdActualMasSid) {
        return _inventoryWdActualMasLocalService.createInventoryWdActualMas(inventoryWdActualMasSid);
    }

    /**
    * Deletes the inventory wd actual mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
    * @return the inventory wd actual mas that was removed
    * @throws PortalException if a inventory wd actual mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas deleteInventoryWdActualMas(
        int inventoryWdActualMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.deleteInventoryWdActualMas(inventoryWdActualMasSid);
    }

    /**
    * Deletes the inventory wd actual mas from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualMas the inventory wd actual mas
    * @return the inventory wd actual mas that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas deleteInventoryWdActualMas(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.deleteInventoryWdActualMas(inventoryWdActualMas);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _inventoryWdActualMasLocalService.dynamicQuery();
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
        return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _inventoryWdActualMasLocalService.dynamicQuery(dynamicQuery,
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
        return _inventoryWdActualMasLocalService.dynamicQueryCount(dynamicQuery);
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
        return _inventoryWdActualMasLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.InventoryWdActualMas fetchInventoryWdActualMas(
        int inventoryWdActualMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.fetchInventoryWdActualMas(inventoryWdActualMasSid);
    }

    /**
    * Returns the inventory wd actual mas with the primary key.
    *
    * @param inventoryWdActualMasSid the primary key of the inventory wd actual mas
    * @return the inventory wd actual mas
    * @throws PortalException if a inventory wd actual mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas getInventoryWdActualMas(
        int inventoryWdActualMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.getInventoryWdActualMas(inventoryWdActualMasSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the inventory wd actual mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdActualMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd actual mases
    * @param end the upper bound of the range of inventory wd actual mases (not inclusive)
    * @return the range of inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.InventoryWdActualMas> getInventoryWdActualMases(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.getInventoryWdActualMases(start,
            end);
    }

    /**
    * Returns the number of inventory wd actual mases.
    *
    * @return the number of inventory wd actual mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getInventoryWdActualMasesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.getInventoryWdActualMasesCount();
    }

    /**
    * Updates the inventory wd actual mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualMas the inventory wd actual mas
    * @return the inventory wd actual mas that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdActualMas updateInventoryWdActualMas(
        com.stpl.app.model.InventoryWdActualMas inventoryWdActualMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdActualMasLocalService.updateInventoryWdActualMas(inventoryWdActualMas);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _inventoryWdActualMasLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _inventoryWdActualMasLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _inventoryWdActualMasLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public InventoryWdActualMasLocalService getWrappedInventoryWdActualMasLocalService() {
        return _inventoryWdActualMasLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedInventoryWdActualMasLocalService(
        InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
        _inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
    }

    @Override
    public InventoryWdActualMasLocalService getWrappedService() {
        return _inventoryWdActualMasLocalService;
    }

    @Override
    public void setWrappedService(
        InventoryWdActualMasLocalService inventoryWdActualMasLocalService) {
        _inventoryWdActualMasLocalService = inventoryWdActualMasLocalService;
    }
}
