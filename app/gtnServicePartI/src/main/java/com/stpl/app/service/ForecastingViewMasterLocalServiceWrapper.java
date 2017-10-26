package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ForecastingViewMasterLocalService}.
 *
 * @author
 * @see ForecastingViewMasterLocalService
 * @generated
 */
public class ForecastingViewMasterLocalServiceWrapper
    implements ForecastingViewMasterLocalService,
        ServiceWrapper<ForecastingViewMasterLocalService> {
    private ForecastingViewMasterLocalService _forecastingViewMasterLocalService;

    public ForecastingViewMasterLocalServiceWrapper(
        ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
        _forecastingViewMasterLocalService = forecastingViewMasterLocalService;
    }

    /**
    * Adds the forecasting view master to the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingViewMaster the forecasting view master
    * @return the forecasting view master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster addForecastingViewMaster(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.addForecastingViewMaster(forecastingViewMaster);
    }

    /**
    * Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
    *
    * @param viewId the primary key for the new forecasting view master
    * @return the new forecasting view master
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster createForecastingViewMaster(
        int viewId) {
        return _forecastingViewMasterLocalService.createForecastingViewMaster(viewId);
    }

    /**
    * Deletes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master that was removed
    * @throws PortalException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster deleteForecastingViewMaster(
        int viewId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.deleteForecastingViewMaster(viewId);
    }

    /**
    * Deletes the forecasting view master from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingViewMaster the forecasting view master
    * @return the forecasting view master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster deleteForecastingViewMaster(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.deleteForecastingViewMaster(forecastingViewMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _forecastingViewMasterLocalService.dynamicQuery();
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
        return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastingViewMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _forecastingViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _forecastingViewMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ForecastingViewMaster fetchForecastingViewMaster(
        int viewId) throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.fetchForecastingViewMaster(viewId);
    }

    /**
    * Returns the forecasting view master with the primary key.
    *
    * @param viewId the primary key of the forecasting view master
    * @return the forecasting view master
    * @throws PortalException if a forecasting view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster getForecastingViewMaster(
        int viewId)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.getForecastingViewMaster(viewId);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the forecasting view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting view masters
    * @param end the upper bound of the range of forecasting view masters (not inclusive)
    * @return the range of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ForecastingViewMaster> getForecastingViewMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.getForecastingViewMasters(start,
            end);
    }

    /**
    * Returns the number of forecasting view masters.
    *
    * @return the number of forecasting view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getForecastingViewMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.getForecastingViewMastersCount();
    }

    /**
    * Updates the forecasting view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param forecastingViewMaster the forecasting view master
    * @return the forecasting view master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingViewMaster updateForecastingViewMaster(
        com.stpl.app.model.ForecastingViewMaster forecastingViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingViewMasterLocalService.updateForecastingViewMaster(forecastingViewMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _forecastingViewMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _forecastingViewMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _forecastingViewMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List findViewByName(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType) {
        return _forecastingViewMasterLocalService.findViewByName(viewName,
            forecastType, userId, viewType);
    }

    @Override
    public java.util.List findViewByNameForChannels(java.lang.String viewName,
        java.lang.String forecastType, java.lang.String userId,
        java.lang.String viewType) {
        return _forecastingViewMasterLocalService.findViewByNameForChannels(viewName,
            forecastType, userId, viewType);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ForecastingViewMasterLocalService getWrappedForecastingViewMasterLocalService() {
        return _forecastingViewMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedForecastingViewMasterLocalService(
        ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
        _forecastingViewMasterLocalService = forecastingViewMasterLocalService;
    }

    @Override
    public ForecastingViewMasterLocalService getWrappedService() {
        return _forecastingViewMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ForecastingViewMasterLocalService forecastingViewMasterLocalService) {
        _forecastingViewMasterLocalService = forecastingViewMasterLocalService;
    }
}
