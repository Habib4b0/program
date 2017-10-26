package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwCompanyMasterLocalService}.
 *
 * @author
 * @see VwCompanyMasterLocalService
 * @generated
 */
public class VwCompanyMasterLocalServiceWrapper
    implements VwCompanyMasterLocalService,
        ServiceWrapper<VwCompanyMasterLocalService> {
    private VwCompanyMasterLocalService _vwCompanyMasterLocalService;

    public VwCompanyMasterLocalServiceWrapper(
        VwCompanyMasterLocalService vwCompanyMasterLocalService) {
        _vwCompanyMasterLocalService = vwCompanyMasterLocalService;
    }

    /**
    * Adds the vw company master to the database. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyMaster the vw company master
    * @return the vw company master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster addVwCompanyMaster(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.addVwCompanyMaster(vwCompanyMaster);
    }

    /**
    * Creates a new vw company master with the primary key. Does not add the vw company master to the database.
    *
    * @param companyMasterSid the primary key for the new vw company master
    * @return the new vw company master
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster createVwCompanyMaster(
        int companyMasterSid) {
        return _vwCompanyMasterLocalService.createVwCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the vw company master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param companyMasterSid the primary key of the vw company master
    * @return the vw company master that was removed
    * @throws PortalException if a vw company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster deleteVwCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.deleteVwCompanyMaster(companyMasterSid);
    }

    /**
    * Deletes the vw company master from the database. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyMaster the vw company master
    * @return the vw company master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster deleteVwCompanyMaster(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.deleteVwCompanyMaster(vwCompanyMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwCompanyMasterLocalService.dynamicQuery();
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
        return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCompanyMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _vwCompanyMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwCompanyMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster fetchVwCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.fetchVwCompanyMaster(companyMasterSid);
    }

    /**
    * Returns the vw company master with the primary key.
    *
    * @param companyMasterSid the primary key of the vw company master
    * @return the vw company master
    * @throws PortalException if a vw company master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster getVwCompanyMaster(
        int companyMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.getVwCompanyMaster(companyMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw company masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCompanyMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw company masters
    * @param end the upper bound of the range of vw company masters (not inclusive)
    * @return the range of vw company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwCompanyMaster> getVwCompanyMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.getVwCompanyMasters(start, end);
    }

    /**
    * Returns the number of vw company masters.
    *
    * @return the number of vw company masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwCompanyMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.getVwCompanyMastersCount();
    }

    /**
    * Updates the vw company master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwCompanyMaster the vw company master
    * @return the vw company master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCompanyMaster updateVwCompanyMaster(
        com.stpl.app.parttwo.model.VwCompanyMaster vwCompanyMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCompanyMasterLocalService.updateVwCompanyMaster(vwCompanyMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwCompanyMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwCompanyMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwCompanyMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwCompanyMasterLocalService getWrappedVwCompanyMasterLocalService() {
        return _vwCompanyMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwCompanyMasterLocalService(
        VwCompanyMasterLocalService vwCompanyMasterLocalService) {
        _vwCompanyMasterLocalService = vwCompanyMasterLocalService;
    }

    @Override
    public VwCompanyMasterLocalService getWrappedService() {
        return _vwCompanyMasterLocalService;
    }

    @Override
    public void setWrappedService(
        VwCompanyMasterLocalService vwCompanyMasterLocalService) {
        _vwCompanyMasterLocalService = vwCompanyMasterLocalService;
    }
}
