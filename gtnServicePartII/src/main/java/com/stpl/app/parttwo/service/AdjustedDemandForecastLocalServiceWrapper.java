package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AdjustedDemandForecastLocalService}.
 *
 * @author
 * @see AdjustedDemandForecastLocalService
 * @generated
 */
public class AdjustedDemandForecastLocalServiceWrapper
    implements AdjustedDemandForecastLocalService,
        ServiceWrapper<AdjustedDemandForecastLocalService> {
    private AdjustedDemandForecastLocalService _adjustedDemandForecastLocalService;

    public AdjustedDemandForecastLocalServiceWrapper(
        AdjustedDemandForecastLocalService adjustedDemandForecastLocalService) {
        _adjustedDemandForecastLocalService = adjustedDemandForecastLocalService;
    }

    /**
    * Adds the adjusted demand forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecast the adjusted demand forecast
    * @return the adjusted demand forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast addAdjustedDemandForecast(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.addAdjustedDemandForecast(adjustedDemandForecast);
    }

    /**
    * Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
    *
    * @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
    * @return the new adjusted demand forecast
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast createAdjustedDemandForecast(
        int adjustedDemandForecastSid) {
        return _adjustedDemandForecastLocalService.createAdjustedDemandForecast(adjustedDemandForecastSid);
    }

    /**
    * Deletes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
    * @return the adjusted demand forecast that was removed
    * @throws PortalException if a adjusted demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast deleteAdjustedDemandForecast(
        int adjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.deleteAdjustedDemandForecast(adjustedDemandForecastSid);
    }

    /**
    * Deletes the adjusted demand forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecast the adjusted demand forecast
    * @return the adjusted demand forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast deleteAdjustedDemandForecast(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.deleteAdjustedDemandForecast(adjustedDemandForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _adjustedDemandForecastLocalService.dynamicQuery();
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
        return _adjustedDemandForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _adjustedDemandForecastLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _adjustedDemandForecastLocalService.dynamicQuery(dynamicQuery,
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
        return _adjustedDemandForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _adjustedDemandForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast fetchAdjustedDemandForecast(
        int adjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.fetchAdjustedDemandForecast(adjustedDemandForecastSid);
    }

    /**
    * Returns the adjusted demand forecast with the primary key.
    *
    * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
    * @return the adjusted demand forecast
    * @throws PortalException if a adjusted demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast getAdjustedDemandForecast(
        int adjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.getAdjustedDemandForecast(adjustedDemandForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the adjusted demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of adjusted demand forecasts
    * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
    * @return the range of adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AdjustedDemandForecast> getAdjustedDemandForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.getAdjustedDemandForecasts(start,
            end);
    }

    /**
    * Returns the number of adjusted demand forecasts.
    *
    * @return the number of adjusted demand forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAdjustedDemandForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.getAdjustedDemandForecastsCount();
    }

    /**
    * Updates the adjusted demand forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param adjustedDemandForecast the adjusted demand forecast
    * @return the adjusted demand forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AdjustedDemandForecast updateAdjustedDemandForecast(
        com.stpl.app.parttwo.model.AdjustedDemandForecast adjustedDemandForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _adjustedDemandForecastLocalService.updateAdjustedDemandForecast(adjustedDemandForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _adjustedDemandForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _adjustedDemandForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _adjustedDemandForecastLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AdjustedDemandForecastLocalService getWrappedAdjustedDemandForecastLocalService() {
        return _adjustedDemandForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAdjustedDemandForecastLocalService(
        AdjustedDemandForecastLocalService adjustedDemandForecastLocalService) {
        _adjustedDemandForecastLocalService = adjustedDemandForecastLocalService;
    }

    @Override
    public AdjustedDemandForecastLocalService getWrappedService() {
        return _adjustedDemandForecastLocalService;
    }

    @Override
    public void setWrappedService(
        AdjustedDemandForecastLocalService adjustedDemandForecastLocalService) {
        _adjustedDemandForecastLocalService = adjustedDemandForecastLocalService;
    }
}
