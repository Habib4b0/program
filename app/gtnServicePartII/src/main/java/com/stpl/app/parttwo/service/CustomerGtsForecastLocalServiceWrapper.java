package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CustomerGtsForecastLocalService}.
 *
 * @author
 * @see CustomerGtsForecastLocalService
 * @generated
 */
public class CustomerGtsForecastLocalServiceWrapper
    implements CustomerGtsForecastLocalService,
        ServiceWrapper<CustomerGtsForecastLocalService> {
    private CustomerGtsForecastLocalService _customerGtsForecastLocalService;

    public CustomerGtsForecastLocalServiceWrapper(
        CustomerGtsForecastLocalService customerGtsForecastLocalService) {
        _customerGtsForecastLocalService = customerGtsForecastLocalService;
    }

    /**
    * Adds the customer gts forecast to the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecast the customer gts forecast
    * @return the customer gts forecast that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast addCustomerGtsForecast(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.addCustomerGtsForecast(customerGtsForecast);
    }

    /**
    * Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
    *
    * @param customerGtsForecastSid the primary key for the new customer gts forecast
    * @return the new customer gts forecast
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast createCustomerGtsForecast(
        int customerGtsForecastSid) {
        return _customerGtsForecastLocalService.createCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Deletes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecastSid the primary key of the customer gts forecast
    * @return the customer gts forecast that was removed
    * @throws PortalException if a customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast deleteCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.deleteCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Deletes the customer gts forecast from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecast the customer gts forecast
    * @return the customer gts forecast that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast deleteCustomerGtsForecast(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.deleteCustomerGtsForecast(customerGtsForecast);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _customerGtsForecastLocalService.dynamicQuery();
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
        return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _customerGtsForecastLocalService.dynamicQuery(dynamicQuery,
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
        return _customerGtsForecastLocalService.dynamicQueryCount(dynamicQuery);
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
        return _customerGtsForecastLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast fetchCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.fetchCustomerGtsForecast(customerGtsForecastSid);
    }

    /**
    * Returns the customer gts forecast with the primary key.
    *
    * @param customerGtsForecastSid the primary key of the customer gts forecast
    * @return the customer gts forecast
    * @throws PortalException if a customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast getCustomerGtsForecast(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.getCustomerGtsForecast(customerGtsForecastSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of customer gts forecasts
    * @param end the upper bound of the range of customer gts forecasts (not inclusive)
    * @return the range of customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> getCustomerGtsForecasts(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.getCustomerGtsForecasts(start,
            end);
    }

    /**
    * Returns the number of customer gts forecasts.
    *
    * @return the number of customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getCustomerGtsForecastsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.getCustomerGtsForecastsCount();
    }

    /**
    * Updates the customer gts forecast in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecast the customer gts forecast
    * @return the customer gts forecast that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.CustomerGtsForecast updateCustomerGtsForecast(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _customerGtsForecastLocalService.updateCustomerGtsForecast(customerGtsForecast);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _customerGtsForecastLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _customerGtsForecastLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _customerGtsForecastLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public CustomerGtsForecastLocalService getWrappedCustomerGtsForecastLocalService() {
        return _customerGtsForecastLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedCustomerGtsForecastLocalService(
        CustomerGtsForecastLocalService customerGtsForecastLocalService) {
        _customerGtsForecastLocalService = customerGtsForecastLocalService;
    }

    @Override
    public CustomerGtsForecastLocalService getWrappedService() {
        return _customerGtsForecastLocalService;
    }

    @Override
    public void setWrappedService(
        CustomerGtsForecastLocalService customerGtsForecastLocalService) {
        _customerGtsForecastLocalService = customerGtsForecastLocalService;
    }
}
