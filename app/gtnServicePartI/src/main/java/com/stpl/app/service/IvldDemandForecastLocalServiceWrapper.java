package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldDemandForecastLocalService}.
 *
 * @author
 * @see IvldDemandForecastLocalService
 * @generated
 */
public class IvldDemandForecastLocalServiceWrapper
    implements IvldDemandForecastLocalService,
        ServiceWrapper<IvldDemandForecastLocalService> {
    private IvldDemandForecastLocalService _ivldDemandForecastLocalService;

    public IvldDemandForecastLocalServiceWrapper(
        IvldDemandForecastLocalService ivldDemandForecastLocalService) {
        _ivldDemandForecastLocalService = ivldDemandForecastLocalService;
    }

    /**
    * Adds the ivld demand forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecast the ivld demand forecast
    * @return the ivld demand forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast addIvldDemandForecast(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.addIvldDemandForecast(ivldDemandForecast);
    }

    /**
    * Creates a new ivld demand forecast with the primary key. Does not add the ivld demand forecast to the database.
    *
    * @param ivldDemandForecastSid the primary key for the new ivld demand forecast
    * @return the new ivld demand forecast
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast createIvldDemandForecast(
        int ivldDemandForecastSid) {
        return _ivldDemandForecastLocalService.createIvldDemandForecast(ivldDemandForecastSid);
    }

    /**
    * Deletes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast that was removed
    * @throws PortalException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast deleteIvldDemandForecast(
        int ivldDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.deleteIvldDemandForecast(ivldDemandForecastSid);
    }

    /**
    * Deletes the ivld demand forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecast the ivld demand forecast
    * @return the ivld demand forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast deleteIvldDemandForecast(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.deleteIvldDemandForecast(ivldDemandForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldDemandForecastLocalService.dynamicQuery();
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
        return _ivldDemandForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldDemandForecastLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldDemandForecastLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldDemandForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldDemandForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldDemandForecast fetchIvldDemandForecast(
        int ivldDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.fetchIvldDemandForecast(ivldDemandForecastSid);
    }

    /**
    * Returns the ivld demand forecast with the primary key.
    *
    * @param ivldDemandForecastSid the primary key of the ivld demand forecast
    * @return the ivld demand forecast
    * @throws PortalException if a ivld demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast getIvldDemandForecast(
        int ivldDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.getIvldDemandForecast(ivldDemandForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand forecasts
    * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
    * @return the range of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldDemandForecast> getIvldDemandForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.getIvldDemandForecasts(start, end);
    }

    /**
    * Returns the number of ivld demand forecasts.
    *
    * @return the number of ivld demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldDemandForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.getIvldDemandForecastsCount();
    }

    /**
    * Updates the ivld demand forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandForecast the ivld demand forecast
    * @return the ivld demand forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandForecast updateIvldDemandForecast(
        com.stpl.app.model.IvldDemandForecast ivldDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandForecastLocalService.updateIvldDemandForecast(ivldDemandForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldDemandForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldDemandForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldDemandForecastLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldDemandForecastLocalService getWrappedIvldDemandForecastLocalService() {
        return _ivldDemandForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldDemandForecastLocalService(
        IvldDemandForecastLocalService ivldDemandForecastLocalService) {
        _ivldDemandForecastLocalService = ivldDemandForecastLocalService;
    }

    @Override
    public IvldDemandForecastLocalService getWrappedService() {
        return _ivldDemandForecastLocalService;
    }

    @Override
    public void setWrappedService(
        IvldDemandForecastLocalService ivldDemandForecastLocalService) {
        _ivldDemandForecastLocalService = ivldDemandForecastLocalService;
    }
}
