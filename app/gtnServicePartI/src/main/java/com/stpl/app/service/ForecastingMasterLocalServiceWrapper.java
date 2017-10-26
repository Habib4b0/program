package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ForecastingMasterLocalService}.
 *
 * @author
 * @see ForecastingMasterLocalService
 * @generated
 */
public class ForecastingMasterLocalServiceWrapper
    implements ForecastingMasterLocalService,
        ServiceWrapper<ForecastingMasterLocalService> {
    private ForecastingMasterLocalService _forecastingMasterLocalService;

    public ForecastingMasterLocalServiceWrapper(
        ForecastingMasterLocalService forecastingMasterLocalService) {
        _forecastingMasterLocalService = forecastingMasterLocalService;
    }

    /**
    * Adds the forecasting master to the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingMaster the forecasting master
    * @return the forecasting master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingMaster addForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.addForecastingMaster(forecastingMaster);
    }

    /**
    * Creates a new forecasting master with the primary key. Does not add the forecasting master to the database.
    *
    * @param forecastMasterSid the primary key for the new forecasting master
    * @return the new forecasting master
    */
    @Override
    public com.stpl.app.model.ForecastingMaster createForecastingMaster(
        int forecastMasterSid) {
        return _forecastingMasterLocalService.createForecastingMaster(forecastMasterSid);
    }

    /**
    * Deletes the forecasting master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastMasterSid the primary key of the forecasting master
    * @return the forecasting master that was removed
    * @throws PortalException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.deleteForecastingMaster(forecastMasterSid);
    }

    /**
    * Deletes the forecasting master from the database. Also notifies the appropriate model listeners.
    *
    * @param forecastingMaster the forecasting master
    * @return the forecasting master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingMaster deleteForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.deleteForecastingMaster(forecastingMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _forecastingMasterLocalService.dynamicQuery();
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
        return _forecastingMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastingMasterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _forecastingMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _forecastingMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _forecastingMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ForecastingMaster fetchForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.fetchForecastingMaster(forecastMasterSid);
    }

    /**
    * Returns the forecasting master with the primary key.
    *
    * @param forecastMasterSid the primary key of the forecasting master
    * @return the forecasting master
    * @throws PortalException if a forecasting master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingMaster getForecastingMaster(
        int forecastMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.getForecastingMaster(forecastMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the forecasting masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ForecastingMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of forecasting masters
    * @param end the upper bound of the range of forecasting masters (not inclusive)
    * @return the range of forecasting masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ForecastingMaster> getForecastingMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.getForecastingMasters(start, end);
    }

    /**
    * Returns the number of forecasting masters.
    *
    * @return the number of forecasting masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getForecastingMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.getForecastingMastersCount();
    }

    /**
    * Updates the forecasting master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param forecastingMaster the forecasting master
    * @return the forecasting master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ForecastingMaster updateForecastingMaster(
        com.stpl.app.model.ForecastingMaster forecastingMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _forecastingMasterLocalService.updateForecastingMaster(forecastingMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _forecastingMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _forecastingMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _forecastingMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List getResults(java.lang.String fileType,
        java.lang.String country, java.lang.String fileName,
        java.lang.String type, java.lang.String version,
        java.lang.String forecastYear, java.lang.String fromDate,
        java.lang.String toDate) {
        return _forecastingMasterLocalService.getResults(fileType, country,
            fileName, type, version, forecastYear, fromDate, toDate);
    }

    @Override
    public java.util.List getDetailsResults(java.lang.String fileName,
        java.lang.String version, java.lang.String fileType,
        java.lang.String country, int year) {
        return _forecastingMasterLocalService.getDetailsResults(fileName,
            version, fileType, country, year);
    }

    @Override
    public java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _forecastingMasterLocalService.executeSelectQuery(query, udc1,
            udc2);
    }

    @Override
    public java.lang.Object executeBulkUpdateQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        return _forecastingMasterLocalService.executeBulkUpdateQuery(query,
            udc1, udc2);
    }

    @Override
    public java.lang.Object executeUpdateQuery(java.util.List<?> nmSalesList,
        java.lang.Object udc1, java.lang.Object udc2, java.lang.Object udc3) {
        return _forecastingMasterLocalService.executeUpdateQuery(nmSalesList,
            udc1, udc2, udc3);
    }

    @Override
    public java.util.List getFileSearchResults(java.lang.String query) {
        return _forecastingMasterLocalService.getFileSearchResults(query);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ForecastingMasterLocalService getWrappedForecastingMasterLocalService() {
        return _forecastingMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedForecastingMasterLocalService(
        ForecastingMasterLocalService forecastingMasterLocalService) {
        _forecastingMasterLocalService = forecastingMasterLocalService;
    }

    @Override
    public ForecastingMasterLocalService getWrappedService() {
        return _forecastingMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ForecastingMasterLocalService forecastingMasterLocalService) {
        _forecastingMasterLocalService = forecastingMasterLocalService;
    }
}
