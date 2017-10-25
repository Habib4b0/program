package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VwCustomerGtsForecastLocalService}.
 *
 * @author
 * @see VwCustomerGtsForecastLocalService
 * @generated
 */
public class VwCustomerGtsForecastLocalServiceWrapper
    implements VwCustomerGtsForecastLocalService,
        ServiceWrapper<VwCustomerGtsForecastLocalService> {
    private VwCustomerGtsForecastLocalService _vwCustomerGtsForecastLocalService;

    public VwCustomerGtsForecastLocalServiceWrapper(
        VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
        _vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
    }

    /**
    * Adds the vw customer gts forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param vwCustomerGtsForecast the vw customer gts forecast
    * @return the vw customer gts forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast addVwCustomerGtsForecast(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.addVwCustomerGtsForecast(vwCustomerGtsForecast);
    }

    /**
    * Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
    *
    * @param customerGtsForecastSid the primary key for the new vw customer gts forecast
    * @return the new vw customer gts forecast
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast createVwCustomerGtsForecast(
        int customerGtsForecastSid) {
        return _vwCustomerGtsForecastLocalService.createVwCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Deletes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecastSid the primary key of the vw customer gts forecast
    * @return the vw customer gts forecast that was removed
    * @throws PortalException if a vw customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast deleteVwCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.deleteVwCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Deletes the vw customer gts forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param vwCustomerGtsForecast the vw customer gts forecast
    * @return the vw customer gts forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast deleteVwCustomerGtsForecast(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.deleteVwCustomerGtsForecast(vwCustomerGtsForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _vwCustomerGtsForecastLocalService.dynamicQuery();
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
        return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _vwCustomerGtsForecastLocalService.dynamicQuery(dynamicQuery,
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
        return _vwCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _vwCustomerGtsForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast fetchVwCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.fetchVwCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Returns the vw customer gts forecast with the primary key.
    *
    * @param customerGtsForecastSid the primary key of the vw customer gts forecast
    * @return the vw customer gts forecast
    * @throws PortalException if a vw customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast getVwCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecast(customerGtsForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the vw customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw customer gts forecasts
    * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
    * @return the range of vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.VwCustomerGtsForecast> getVwCustomerGtsForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecasts(start,
            end);
    }

    /**
    * Returns the number of vw customer gts forecasts.
    *
    * @return the number of vw customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getVwCustomerGtsForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.getVwCustomerGtsForecastsCount();
    }

    /**
    * Updates the vw customer gts forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param vwCustomerGtsForecast the vw customer gts forecast
    * @return the vw customer gts forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.VwCustomerGtsForecast updateVwCustomerGtsForecast(
        com.stpl.app.parttwo.model.VwCustomerGtsForecast vwCustomerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _vwCustomerGtsForecastLocalService.updateVwCustomerGtsForecast(vwCustomerGtsForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _vwCustomerGtsForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _vwCustomerGtsForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _vwCustomerGtsForecastLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public VwCustomerGtsForecastLocalService getWrappedVwCustomerGtsForecastLocalService() {
        return _vwCustomerGtsForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedVwCustomerGtsForecastLocalService(
        VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
        _vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
    }

    @Override
    public VwCustomerGtsForecastLocalService getWrappedService() {
        return _vwCustomerGtsForecastLocalService;
    }

    @Override
    public void setWrappedService(
        VwCustomerGtsForecastLocalService vwCustomerGtsForecastLocalService) {
        _vwCustomerGtsForecastLocalService = vwCustomerGtsForecastLocalService;
    }
}
