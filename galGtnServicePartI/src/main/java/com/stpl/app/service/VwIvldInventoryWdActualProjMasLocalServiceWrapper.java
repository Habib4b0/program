package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwIvldInventoryWdActualProjMasLocalService}.
 *
 * @author
 * @see VwIvldInventoryWdActualProjMasLocalService
 * @generated
 */
public class VwIvldInventoryWdActualProjMasLocalServiceWrapper
    implements VwIvldInventoryWdActualProjMasLocalService,
        ServiceWrapper<VwIvldInventoryWdActualProjMasLocalService> {
    private VwIvldInventoryWdActualProjMasLocalService _vwIvldInventoryWdActualProjMasLocalService;

    public VwIvldInventoryWdActualProjMasLocalServiceWrapper(
        VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
        _vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
    }

    /**
    * Adds the vw ivld inventory wd actual proj mas to the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas addVwIvldInventoryWdActualProjMas(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.addVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
    }

    /**
    * Creates a new vw ivld inventory wd actual proj mas with the primary key. Does not add the vw ivld inventory wd actual proj mas to the database.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key for the new vw ivld inventory wd actual proj mas
    * @return the new vw ivld inventory wd actual proj mas
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas createVwIvldInventoryWdActualProjMas(
        int ivldInventoryWdActualProjMasSid) {
        return _vwIvldInventoryWdActualProjMasLocalService.createVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
    }

    /**
    * Deletes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas that was removed
    * @throws PortalException if a vw ivld inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas deleteVwIvldInventoryWdActualProjMas(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.deleteVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
    }

    /**
    * Deletes the vw ivld inventory wd actual proj mas from the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas deleteVwIvldInventoryWdActualProjMas(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.deleteVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery();
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
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQuery(dynamicQuery,
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
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwIvldInventoryWdActualProjMasLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas fetchVwIvldInventoryWdActualProjMas(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.fetchVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
    }

    /**
    * Returns the vw ivld inventory wd actual proj mas with the primary key.
    *
    * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas
    * @throws PortalException if a vw ivld inventory wd actual proj mas with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas getVwIvldInventoryWdActualProjMas(
        int ivldInventoryWdActualProjMasSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMas(ivldInventoryWdActualProjMasSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw ivld inventory wd actual proj mases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
    * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
    * @return the range of vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.VwIvldInventoryWdActualProjMas> getVwIvldInventoryWdActualProjMases(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMases(start,
            end);
    }

    /**
    * Returns the number of vw ivld inventory wd actual proj mases.
    *
    * @return the number of vw ivld inventory wd actual proj mases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwIvldInventoryWdActualProjMasesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.getVwIvldInventoryWdActualProjMasesCount();
    }

    /**
    * Updates the vw ivld inventory wd actual proj mas in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
    * @return the vw ivld inventory wd actual proj mas that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwIvldInventoryWdActualProjMas updateVwIvldInventoryWdActualProjMas(
        com.stpl.app.model.VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldInventoryWdActualProjMasLocalService.updateVwIvldInventoryWdActualProjMas(vwIvldInventoryWdActualProjMas);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwIvldInventoryWdActualProjMasLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwIvldInventoryWdActualProjMasLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwIvldInventoryWdActualProjMasLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwIvldInventoryWdActualProjMasLocalService getWrappedVwIvldInventoryWdActualProjMasLocalService() {
        return _vwIvldInventoryWdActualProjMasLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwIvldInventoryWdActualProjMasLocalService(
        VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
        _vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
    }

    @Override
    public VwIvldInventoryWdActualProjMasLocalService getWrappedService() {
        return _vwIvldInventoryWdActualProjMasLocalService;
    }

    @Override
    public void setWrappedService(
        VwIvldInventoryWdActualProjMasLocalService vwIvldInventoryWdActualProjMasLocalService) {
        _vwIvldInventoryWdActualProjMasLocalService = vwIvldInventoryWdActualProjMasLocalService;
    }
}
