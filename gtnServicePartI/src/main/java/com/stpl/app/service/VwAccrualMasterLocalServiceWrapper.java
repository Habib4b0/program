package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwAccrualMasterLocalService}.
 *
 * @author
 * @see VwAccrualMasterLocalService
 * @generated
 */
public class VwAccrualMasterLocalServiceWrapper
    implements VwAccrualMasterLocalService,
        ServiceWrapper<VwAccrualMasterLocalService> {
    private VwAccrualMasterLocalService _vwAccrualMasterLocalService;

    public VwAccrualMasterLocalServiceWrapper(
        VwAccrualMasterLocalService vwAccrualMasterLocalService) {
        _vwAccrualMasterLocalService = vwAccrualMasterLocalService;
    }

    /**
    * Adds the vw accrual master to the database. Also notifies the appropriate model listeners.
    *
    * @param vwAccrualMaster the vw accrual master
    * @return the vw accrual master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster addVwAccrualMaster(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.addVwAccrualMaster(vwAccrualMaster);
    }

    /**
    * Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
    *
    * @param accrualMasterSid the primary key for the new vw accrual master
    * @return the new vw accrual master
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster createVwAccrualMaster(
        int accrualMasterSid) {
        return _vwAccrualMasterLocalService.createVwAccrualMaster(accrualMasterSid);
    }

    /**
    * Deletes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMasterSid the primary key of the vw accrual master
    * @return the vw accrual master that was removed
    * @throws PortalException if a vw accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster deleteVwAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.deleteVwAccrualMaster(accrualMasterSid);
    }

    /**
    * Deletes the vw accrual master from the database. Also notifies the appropriate model listeners.
    *
    * @param vwAccrualMaster the vw accrual master
    * @return the vw accrual master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster deleteVwAccrualMaster(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.deleteVwAccrualMaster(vwAccrualMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwAccrualMasterLocalService.dynamicQuery();
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
        return _vwAccrualMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwAccrualMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwAccrualMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _vwAccrualMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwAccrualMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.VwAccrualMaster fetchVwAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.fetchVwAccrualMaster(accrualMasterSid);
    }

    /**
    * Returns the vw accrual master with the primary key.
    *
    * @param accrualMasterSid the primary key of the vw accrual master
    * @return the vw accrual master
    * @throws PortalException if a vw accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster getVwAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.getVwAccrualMaster(accrualMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw accrual masters
    * @param end the upper bound of the range of vw accrual masters (not inclusive)
    * @return the range of vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.VwAccrualMaster> getVwAccrualMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.getVwAccrualMasters(start, end);
    }

    /**
    * Returns the number of vw accrual masters.
    *
    * @return the number of vw accrual masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwAccrualMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.getVwAccrualMastersCount();
    }

    /**
    * Updates the vw accrual master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwAccrualMaster the vw accrual master
    * @return the vw accrual master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.VwAccrualMaster updateVwAccrualMaster(
        com.stpl.app.model.VwAccrualMaster vwAccrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwAccrualMasterLocalService.updateVwAccrualMaster(vwAccrualMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwAccrualMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwAccrualMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwAccrualMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwAccrualMasterLocalService getWrappedVwAccrualMasterLocalService() {
        return _vwAccrualMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwAccrualMasterLocalService(
        VwAccrualMasterLocalService vwAccrualMasterLocalService) {
        _vwAccrualMasterLocalService = vwAccrualMasterLocalService;
    }

    @Override
    public VwAccrualMasterLocalService getWrappedService() {
        return _vwAccrualMasterLocalService;
    }

    @Override
    public void setWrappedService(
        VwAccrualMasterLocalService vwAccrualMasterLocalService) {
        _vwAccrualMasterLocalService = vwAccrualMasterLocalService;
    }
}
