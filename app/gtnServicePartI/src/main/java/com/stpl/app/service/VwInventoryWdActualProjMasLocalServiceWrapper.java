package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwInventoryWdActualProjMasLocalService}.
 *
 * @author
 * @see VwInventoryWdActualProjMasLocalService
 * @generated
 */
public class VwInventoryWdActualProjMasLocalServiceWrapper
    implements VwInventoryWdActualProjMasLocalService,
        ServiceWrapper<VwInventoryWdActualProjMasLocalService> {
    private VwInventoryWdActualProjMasLocalService _vwInventoryWdActualProjMasLocalService;

    public VwInventoryWdActualProjMasLocalServiceWrapper(
        VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
        _vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
    }

    /**
    * Adds the vw inventory wd actual proj mas to the database. Also notifies the appropriate model listeners.
    *
    * @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
    * @return the vw inventory wd actual proj mas that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas addVwInventoryWdActualProjMas(
        com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.addVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
    }

    /**
    * Creates a new vw inventory wd actual proj mas with the primary key. Does not add the vw inventory wd actual proj mas to the database.
    *
    * @param inventoryWdActualProjMasSid the primary key for the new vw inventory wd actual proj mas
    * @return the new vw inventory wd actual proj mas
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas createVwInventoryWdActualProjMas(
        int inventoryWdActualProjMasSid) {
        return _vwInventoryWdActualProjMasLocalService.createVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
    }

    /**
    * Deletes the vw inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
    * @return the vw inventory wd actual proj mas that was removed
    * @throws PortalException if a vw inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas deleteVwInventoryWdActualProjMas(
        int inventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.deleteVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
    }

    /**
    * Deletes the vw inventory wd actual proj mas from the database. Also notifies the appropriate model listeners.
    *
    * @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
    * @return the vw inventory wd actual proj mas that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas deleteVwInventoryWdActualProjMas(
        com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.deleteVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwInventoryWdActualProjMasLocalService.dynamicQuery();
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
        return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
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
        return _vwInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas fetchVwInventoryWdActualProjMas(
        int inventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.fetchVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
    }

    /**
    * Returns the vw inventory wd actual proj mas with the primary key.
    *
    * @param inventoryWdActualProjMasSid the primary key of the vw inventory wd actual proj mas
    * @return the vw inventory wd actual proj mas
    * @throws PortalException if a vw inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas getVwInventoryWdActualProjMas(
        int inventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMas(inventoryWdActualProjMasSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw inventory wd actual proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw inventory wd actual proj mases
    * @param end the upper bound of the range of vw inventory wd actual proj mases (not inclusive)
    * @return the range of vw inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.VwInventoryWdActualProjMas> getVwInventoryWdActualProjMases(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMases(start,
            end);
    }

    /**
    * Returns the number of vw inventory wd actual proj mases.
    *
    * @return the number of vw inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwInventoryWdActualProjMasesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.getVwInventoryWdActualProjMasesCount();
    }

    /**
    * Updates the vw inventory wd actual proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwInventoryWdActualProjMas the vw inventory wd actual proj mas
    * @return the vw inventory wd actual proj mas that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwInventoryWdActualProjMas updateVwInventoryWdActualProjMas(
        com.stpl.app.model.VwInventoryWdActualProjMas vwInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwInventoryWdActualProjMasLocalService.updateVwInventoryWdActualProjMas(vwInventoryWdActualProjMas);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwInventoryWdActualProjMasLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwInventoryWdActualProjMasLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwInventoryWdActualProjMasLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwInventoryWdActualProjMasLocalService getWrappedVwInventoryWdActualProjMasLocalService() {
        return _vwInventoryWdActualProjMasLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwInventoryWdActualProjMasLocalService(
        VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
        _vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
    }

    @Override
    public VwInventoryWdActualProjMasLocalService getWrappedService() {
        return _vwInventoryWdActualProjMasLocalService;
    }

    @Override
    public void setWrappedService(
        VwInventoryWdActualProjMasLocalService vwInventoryWdActualProjMasLocalService) {
        _vwInventoryWdActualProjMasLocalService = vwInventoryWdActualProjMasLocalService;
    }
}
