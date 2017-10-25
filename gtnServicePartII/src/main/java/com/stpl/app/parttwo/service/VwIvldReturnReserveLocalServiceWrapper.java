package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwIvldReturnReserveLocalService}.
 *
 * @author
 * @see VwIvldReturnReserveLocalService
 * @generated
 */
public class VwIvldReturnReserveLocalServiceWrapper
    implements VwIvldReturnReserveLocalService,
        ServiceWrapper<VwIvldReturnReserveLocalService> {
    private VwIvldReturnReserveLocalService _vwIvldReturnReserveLocalService;

    public VwIvldReturnReserveLocalServiceWrapper(
        VwIvldReturnReserveLocalService vwIvldReturnReserveLocalService) {
        _vwIvldReturnReserveLocalService = vwIvldReturnReserveLocalService;
    }

    /**
    * Adds the vw ivld return reserve to the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldReturnReserve the vw ivld return reserve
    * @return the vw ivld return reserve that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve addVwIvldReturnReserve(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.addVwIvldReturnReserve(vwIvldReturnReserve);
    }

    /**
    * Creates a new vw ivld return reserve with the primary key. Does not add the vw ivld return reserve to the database.
    *
    * @param ivldReturnReserveSid the primary key for the new vw ivld return reserve
    * @return the new vw ivld return reserve
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve createVwIvldReturnReserve(
        int ivldReturnReserveSid) {
        return _vwIvldReturnReserveLocalService.createVwIvldReturnReserve(ivldReturnReserveSid);
    }

    /**
    * Deletes the vw ivld return reserve with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve that was removed
    * @throws PortalException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve deleteVwIvldReturnReserve(
        int ivldReturnReserveSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.deleteVwIvldReturnReserve(ivldReturnReserveSid);
    }

    /**
    * Deletes the vw ivld return reserve from the database. Also notifies the appropriate model listeners.
    *
    * @param vwIvldReturnReserve the vw ivld return reserve
    * @return the vw ivld return reserve that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve deleteVwIvldReturnReserve(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.deleteVwIvldReturnReserve(vwIvldReturnReserve);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwIvldReturnReserveLocalService.dynamicQuery();
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
        return _vwIvldReturnReserveLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldReturnReserveLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwIvldReturnReserveLocalService.dynamicQuery(dynamicQuery,
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
        return _vwIvldReturnReserveLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwIvldReturnReserveLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve fetchVwIvldReturnReserve(
        int ivldReturnReserveSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.fetchVwIvldReturnReserve(ivldReturnReserveSid);
    }

    /**
    * Returns the vw ivld return reserve with the primary key.
    *
    * @param ivldReturnReserveSid the primary key of the vw ivld return reserve
    * @return the vw ivld return reserve
    * @throws PortalException if a vw ivld return reserve with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve getVwIvldReturnReserve(
        int ivldReturnReserveSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.getVwIvldReturnReserve(ivldReturnReserveSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw ivld return reserves.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldReturnReserveModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld return reserves
    * @param end the upper bound of the range of vw ivld return reserves (not inclusive)
    * @return the range of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwIvldReturnReserve> getVwIvldReturnReserves(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.getVwIvldReturnReserves(start,
            end);
    }

    /**
    * Returns the number of vw ivld return reserves.
    *
    * @return the number of vw ivld return reserves
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwIvldReturnReservesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.getVwIvldReturnReservesCount();
    }

    /**
    * Updates the vw ivld return reserve in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwIvldReturnReserve the vw ivld return reserve
    * @return the vw ivld return reserve that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwIvldReturnReserve updateVwIvldReturnReserve(
        com.stpl.app.parttwo.model.VwIvldReturnReserve vwIvldReturnReserve)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwIvldReturnReserveLocalService.updateVwIvldReturnReserve(vwIvldReturnReserve);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwIvldReturnReserveLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwIvldReturnReserveLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwIvldReturnReserveLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwIvldReturnReserveLocalService getWrappedVwIvldReturnReserveLocalService() {
        return _vwIvldReturnReserveLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwIvldReturnReserveLocalService(
        VwIvldReturnReserveLocalService vwIvldReturnReserveLocalService) {
        _vwIvldReturnReserveLocalService = vwIvldReturnReserveLocalService;
    }

    @Override
    public VwIvldReturnReserveLocalService getWrappedService() {
        return _vwIvldReturnReserveLocalService;
    }

    @Override
    public void setWrappedService(
        VwIvldReturnReserveLocalService vwIvldReturnReserveLocalService) {
        _vwIvldReturnReserveLocalService = vwIvldReturnReserveLocalService;
    }
}
