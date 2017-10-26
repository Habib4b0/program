package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ForecastConfigLocalService}.
 *
 * @author
 * @see ForecastConfigLocalService
 * @generated
 */
public class ForecastConfigLocalServiceWrapper
    implements ForecastConfigLocalService,
        ServiceWrapper<ForecastConfigLocalService> {
    private ForecastConfigLocalService _forecastConfigLocalService;

    public ForecastConfigLocalServiceWrapper(
        ForecastConfigLocalService forecastConfigLocalService) {
        _forecastConfigLocalService = forecastConfigLocalService;
    }

    /**
    * Adds the forecast config to the database. Also notifies the appropriate model listeners.
    *
    * @param forecastConfig the forecast config
    * @return the forecast config that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastConfig addForecastConfig(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.addForecastConfig(forecastConfig);
    }

    /**
    * Creates a new forecast config with the primary key. Does not add the forecast config to the database.
    *
    * @param forecastConfigSid the primary key for the new forecast config
    * @return the new forecast config
    */
    @Override
    public com.stpl.app.model.ForecastConfig createForecastConfig(
        int forecastConfigSid) {
        return _forecastConfigLocalService.createForecastConfig(forecastConfigSid);
    }

    /**
    * Deletes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config that was removed
    * @throws PortalException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastConfig deleteForecastConfig(
        int forecastConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.deleteForecastConfig(forecastConfigSid);
    }

    /**
    * Deletes the forecast config from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastConfig the forecast config
    * @return the forecast config that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastConfig deleteForecastConfig(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.deleteForecastConfig(forecastConfig);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _forecastConfigLocalService.dynamicQuery();
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
        return _forecastConfigLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastConfigLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastConfigLocalService.dynamicQuery(dynamicQuery, start,
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
        return _forecastConfigLocalService.dynamicQueryCount(dynamicQuery);
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
        return _forecastConfigLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ForecastConfig fetchForecastConfig(
        int forecastConfigSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.fetchForecastConfig(forecastConfigSid);
    }

    /**
    * Returns the forecast config with the primary key.
    *
    * @param forecastConfigSid the primary key of the forecast config
    * @return the forecast config
    * @throws PortalException if a forecast config with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastConfig getForecastConfig(
        int forecastConfigSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.getForecastConfig(forecastConfigSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the forecast configs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecast configs
    * @param end the upper bound of the range of forecast configs (not inclusive)
    * @return the range of forecast configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ForecastConfig> getForecastConfigs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.getForecastConfigs(start, end);
    }

    /**
    * Returns the number of forecast configs.
    *
    * @return the number of forecast configs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getForecastConfigsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.getForecastConfigsCount();
    }

    /**
    * Updates the forecast config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param forecastConfig the forecast config
    * @return the forecast config that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastConfig updateForecastConfig(
        com.stpl.app.model.ForecastConfig forecastConfig)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastConfigLocalService.updateForecastConfig(forecastConfig);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _forecastConfigLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _forecastConfigLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _forecastConfigLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ForecastConfigLocalService getWrappedForecastConfigLocalService() {
        return _forecastConfigLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedForecastConfigLocalService(
        ForecastConfigLocalService forecastConfigLocalService) {
        _forecastConfigLocalService = forecastConfigLocalService;
    }

    @Override
    public ForecastConfigLocalService getWrappedService() {
        return _forecastConfigLocalService;
    }

    @Override
    public void setWrappedService(
        ForecastConfigLocalService forecastConfigLocalService) {
        _forecastConfigLocalService = forecastConfigLocalService;
    }
}
