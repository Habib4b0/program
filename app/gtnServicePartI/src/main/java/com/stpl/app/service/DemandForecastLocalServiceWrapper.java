package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DemandForecastLocalService}.
 *
 * @author
 * @see DemandForecastLocalService
 * @generated
 */
public class DemandForecastLocalServiceWrapper
    implements DemandForecastLocalService,
        ServiceWrapper<DemandForecastLocalService> {
    private DemandForecastLocalService _demandForecastLocalService;

    public DemandForecastLocalServiceWrapper(
        DemandForecastLocalService demandForecastLocalService) {
        _demandForecastLocalService = demandForecastLocalService;
    }

    /**
    * Adds the demand forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecast the demand forecast
    * @return the demand forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DemandForecast addDemandForecast(
        com.stpl.app.model.DemandForecast demandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.addDemandForecast(demandForecast);
    }

    /**
    * Creates a new demand forecast with the primary key. Does not add the demand forecast to the database.
    *
    * @param demandForecastSid the primary key for the new demand forecast
    * @return the new demand forecast
    */
    @Override
    public com.stpl.app.model.DemandForecast createDemandForecast(
        int demandForecastSid) {
        return _demandForecastLocalService.createDemandForecast(demandForecastSid);
    }

    /**
    * Deletes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast that was removed
    * @throws PortalException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DemandForecast deleteDemandForecast(
        int demandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.deleteDemandForecast(demandForecastSid);
    }

    /**
    * Deletes the demand forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecast the demand forecast
    * @return the demand forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DemandForecast deleteDemandForecast(
        com.stpl.app.model.DemandForecast demandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.deleteDemandForecast(demandForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _demandForecastLocalService.dynamicQuery();
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
        return _demandForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _demandForecastLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _demandForecastLocalService.dynamicQuery(dynamicQuery, start,
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
        return _demandForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _demandForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.DemandForecast fetchDemandForecast(
        int demandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.fetchDemandForecast(demandForecastSid);
    }

    /**
    * Returns the demand forecast with the primary key.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast
    * @throws PortalException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DemandForecast getDemandForecast(
        int demandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.getDemandForecast(demandForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of demand forecasts
    * @param end the upper bound of the range of demand forecasts (not inclusive)
    * @return the range of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.DemandForecast> getDemandForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.getDemandForecasts(start, end);
    }

    /**
    * Returns the number of demand forecasts.
    *
    * @return the number of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDemandForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.getDemandForecastsCount();
    }

    /**
    * Updates the demand forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param demandForecast the demand forecast
    * @return the demand forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.DemandForecast updateDemandForecast(
        com.stpl.app.model.DemandForecast demandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _demandForecastLocalService.updateDemandForecast(demandForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _demandForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _demandForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _demandForecastLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DemandForecastLocalService getWrappedDemandForecastLocalService() {
        return _demandForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDemandForecastLocalService(
        DemandForecastLocalService demandForecastLocalService) {
        _demandForecastLocalService = demandForecastLocalService;
    }

    @Override
    public DemandForecastLocalService getWrappedService() {
        return _demandForecastLocalService;
    }

    @Override
    public void setWrappedService(
        DemandForecastLocalService demandForecastLocalService) {
        _demandForecastLocalService = demandForecastLocalService;
    }
}
