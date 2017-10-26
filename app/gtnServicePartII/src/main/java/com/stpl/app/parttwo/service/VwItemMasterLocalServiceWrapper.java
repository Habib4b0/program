package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwItemMasterLocalService}.
 *
 * @author
 * @see VwItemMasterLocalService
 * @generated
 */
public class VwItemMasterLocalServiceWrapper implements VwItemMasterLocalService,
    ServiceWrapper<VwItemMasterLocalService> {
    private VwItemMasterLocalService _vwItemMasterLocalService;

    public VwItemMasterLocalServiceWrapper(
        VwItemMasterLocalService vwItemMasterLocalService) {
        _vwItemMasterLocalService = vwItemMasterLocalService;
    }

    /**
    * Adds the vw item master to the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemMaster the vw item master
    * @return the vw item master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster addVwItemMaster(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.addVwItemMaster(vwItemMaster);
    }

    /**
    * Creates a new vw item master with the primary key. Does not add the vw item master to the database.
    *
    * @param itemMasterSid the primary key for the new vw item master
    * @return the new vw item master
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster createVwItemMaster(
        int itemMasterSid) {
        return _vwItemMasterLocalService.createVwItemMaster(itemMasterSid);
    }

    /**
    * Deletes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master that was removed
    * @throws PortalException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster deleteVwItemMaster(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.deleteVwItemMaster(itemMasterSid);
    }

    /**
    * Deletes the vw item master from the database. Also notifies the appropriate model listeners.
    *
    * @param vwItemMaster the vw item master
    * @return the vw item master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster deleteVwItemMaster(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.deleteVwItemMaster(vwItemMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwItemMasterLocalService.dynamicQuery();
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
        return _vwItemMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwItemMasterLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _vwItemMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwItemMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwItemMaster fetchVwItemMaster(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.fetchVwItemMaster(itemMasterSid);
    }

    /**
    * Returns the vw item master with the primary key.
    *
    * @param itemMasterSid the primary key of the vw item master
    * @return the vw item master
    * @throws PortalException if a vw item master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster getVwItemMaster(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.getVwItemMaster(itemMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw item masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw item masters
    * @param end the upper bound of the range of vw item masters (not inclusive)
    * @return the range of vw item masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwItemMaster> getVwItemMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.getVwItemMasters(start, end);
    }

    /**
    * Returns the number of vw item masters.
    *
    * @return the number of vw item masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwItemMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.getVwItemMastersCount();
    }

    /**
    * Updates the vw item master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwItemMaster the vw item master
    * @return the vw item master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwItemMaster updateVwItemMaster(
        com.stpl.app.parttwo.model.VwItemMaster vwItemMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwItemMasterLocalService.updateVwItemMaster(vwItemMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwItemMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwItemMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwItemMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwItemMasterLocalService getWrappedVwItemMasterLocalService() {
        return _vwItemMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwItemMasterLocalService(
        VwItemMasterLocalService vwItemMasterLocalService) {
        _vwItemMasterLocalService = vwItemMasterLocalService;
    }

    @Override
    public VwItemMasterLocalService getWrappedService() {
        return _vwItemMasterLocalService;
    }

    @Override
    public void setWrappedService(
        VwItemMasterLocalService vwItemMasterLocalService) {
        _vwItemMasterLocalService = vwItemMasterLocalService;
    }
}
