package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccrualMasterLocalService}.
 *
 * @author
 * @see AccrualMasterLocalService
 * @generated
 */
public class AccrualMasterLocalServiceWrapper
    implements AccrualMasterLocalService,
        ServiceWrapper<AccrualMasterLocalService> {
    private AccrualMasterLocalService _accrualMasterLocalService;

    public AccrualMasterLocalServiceWrapper(
        AccrualMasterLocalService accrualMasterLocalService) {
        _accrualMasterLocalService = accrualMasterLocalService;
    }

    /**
    * Adds the accrual master to the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMaster the accrual master
    * @return the accrual master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualMaster addAccrualMaster(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.addAccrualMaster(accrualMaster);
    }

    /**
    * Creates a new accrual master with the primary key. Does not add the accrual master to the database.
    *
    * @param accrualMasterSid the primary key for the new accrual master
    * @return the new accrual master
    */
    @Override
    public com.stpl.app.model.AccrualMaster createAccrualMaster(
        int accrualMasterSid) {
        return _accrualMasterLocalService.createAccrualMaster(accrualMasterSid);
    }

    /**
    * Deletes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master that was removed
    * @throws PortalException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualMaster deleteAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.deleteAccrualMaster(accrualMasterSid);
    }

    /**
    * Deletes the accrual master from the database. Also notifies the appropriate model listeners.
    *
    * @param accrualMaster the accrual master
    * @return the accrual master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualMaster deleteAccrualMaster(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.deleteAccrualMaster(accrualMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _accrualMasterLocalService.dynamicQuery();
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
        return _accrualMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accrualMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accrualMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _accrualMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _accrualMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.AccrualMaster fetchAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.fetchAccrualMaster(accrualMasterSid);
    }

    /**
    * Returns the accrual master with the primary key.
    *
    * @param accrualMasterSid the primary key of the accrual master
    * @return the accrual master
    * @throws PortalException if a accrual master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualMaster getAccrualMaster(
        int accrualMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.getAccrualMaster(accrualMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the accrual masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of accrual masters
    * @param end the upper bound of the range of accrual masters (not inclusive)
    * @return the range of accrual masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.AccrualMaster> getAccrualMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.getAccrualMasters(start, end);
    }

    /**
    * Returns the number of accrual masters.
    *
    * @return the number of accrual masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAccrualMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.getAccrualMastersCount();
    }

    /**
    * Updates the accrual master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accrualMaster the accrual master
    * @return the accrual master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.AccrualMaster updateAccrualMaster(
        com.stpl.app.model.AccrualMaster accrualMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accrualMasterLocalService.updateAccrualMaster(accrualMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _accrualMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _accrualMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _accrualMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AccrualMasterLocalService getWrappedAccrualMasterLocalService() {
        return _accrualMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAccrualMasterLocalService(
        AccrualMasterLocalService accrualMasterLocalService) {
        _accrualMasterLocalService = accrualMasterLocalService;
    }

    @Override
    public AccrualMasterLocalService getWrappedService() {
        return _accrualMasterLocalService;
    }

    @Override
    public void setWrappedService(
        AccrualMasterLocalService accrualMasterLocalService) {
        _accrualMasterLocalService = accrualMasterLocalService;
    }
}
