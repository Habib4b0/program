package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InventoryWdProjMasLocalService}.
 *
 * @author
 * @see InventoryWdProjMasLocalService
 * @generated
 */
public class InventoryWdProjMasLocalServiceWrapper
    implements InventoryWdProjMasLocalService,
        ServiceWrapper<InventoryWdProjMasLocalService> {
    private InventoryWdProjMasLocalService _inventoryWdProjMasLocalService;

    public InventoryWdProjMasLocalServiceWrapper(
        InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
        _inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
    }

    /**
    * Adds the inventory wd proj mas to the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMas the inventory wd proj mas
    * @return the inventory wd proj mas that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas addInventoryWdProjMas(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.addInventoryWdProjMas(inventoryWdProjMas);
    }

    /**
    * Creates a new inventory wd proj mas with the primary key. Does not add the inventory wd proj mas to the database.
    *
    * @param inventoryWdProjMasSid the primary key for the new inventory wd proj mas
    * @return the new inventory wd proj mas
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas createInventoryWdProjMas(
        int inventoryWdProjMasSid) {
        return _inventoryWdProjMasLocalService.createInventoryWdProjMas(inventoryWdProjMasSid);
    }

    /**
    * Deletes the inventory wd proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas that was removed
    * @throws PortalException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas deleteInventoryWdProjMas(
        int inventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.deleteInventoryWdProjMas(inventoryWdProjMasSid);
    }

    /**
    * Deletes the inventory wd proj mas from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMas the inventory wd proj mas
    * @return the inventory wd proj mas that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas deleteInventoryWdProjMas(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.deleteInventoryWdProjMas(inventoryWdProjMas);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _inventoryWdProjMasLocalService.dynamicQuery();
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
        return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _inventoryWdProjMasLocalService.dynamicQuery(dynamicQuery,
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
        return _inventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
        return _inventoryWdProjMasLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.InventoryWdProjMas fetchInventoryWdProjMas(
        int inventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.fetchInventoryWdProjMas(inventoryWdProjMasSid);
    }

    /**
    * Returns the inventory wd proj mas with the primary key.
    *
    * @param inventoryWdProjMasSid the primary key of the inventory wd proj mas
    * @return the inventory wd proj mas
    * @throws PortalException if a inventory wd proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas getInventoryWdProjMas(
        int inventoryWdProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.getInventoryWdProjMas(inventoryWdProjMasSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the inventory wd proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.InventoryWdProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of inventory wd proj mases
    * @param end the upper bound of the range of inventory wd proj mases (not inclusive)
    * @return the range of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.InventoryWdProjMas> getInventoryWdProjMases(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.getInventoryWdProjMases(start,
            end);
    }

    /**
    * Returns the number of inventory wd proj mases.
    *
    * @return the number of inventory wd proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getInventoryWdProjMasesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.getInventoryWdProjMasesCount();
    }

    /**
    * Updates the inventory wd proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdProjMas the inventory wd proj mas
    * @return the inventory wd proj mas that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.InventoryWdProjMas updateInventoryWdProjMas(
        com.stpl.app.model.InventoryWdProjMas inventoryWdProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _inventoryWdProjMasLocalService.updateInventoryWdProjMas(inventoryWdProjMas);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _inventoryWdProjMasLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _inventoryWdProjMasLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _inventoryWdProjMasLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public InventoryWdProjMasLocalService getWrappedInventoryWdProjMasLocalService() {
        return _inventoryWdProjMasLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedInventoryWdProjMasLocalService(
        InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
        _inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
    }

    @Override
    public InventoryWdProjMasLocalService getWrappedService() {
        return _inventoryWdProjMasLocalService;
    }

    @Override
    public void setWrappedService(
        InventoryWdProjMasLocalService inventoryWdProjMasLocalService) {
        _inventoryWdProjMasLocalService = inventoryWdProjMasLocalService;
    }
}
