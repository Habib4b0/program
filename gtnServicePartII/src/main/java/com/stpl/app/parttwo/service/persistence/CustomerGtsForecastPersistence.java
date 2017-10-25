package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CustomerGtsForecast;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsForecastPersistenceImpl
 * @see CustomerGtsForecastUtil
 * @generated
 */
public interface CustomerGtsForecastPersistence extends BasePersistence<CustomerGtsForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CustomerGtsForecastUtil} to access the customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the customer gts forecast in the entity cache if it is enabled.
    *
    * @param customerGtsForecast the customer gts forecast
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast);

    /**
    * Caches the customer gts forecasts in the entity cache if it is enabled.
    *
    * @param customerGtsForecasts the customer gts forecasts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> customerGtsForecasts);

    /**
    * Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
    *
    * @param customerGtsForecastSid the primary key for the new customer gts forecast
    * @return the new customer gts forecast
    */
    public com.stpl.app.parttwo.model.CustomerGtsForecast create(
        int customerGtsForecastSid);

    /**
    * Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customerGtsForecastSid the primary key of the customer gts forecast
    * @return the customer gts forecast that was removed
    * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsForecast remove(
        int customerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CustomerGtsForecast updateImpl(
        com.stpl.app.parttwo.model.CustomerGtsForecast customerGtsForecast)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the customer gts forecast with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCustomerGtsForecastException} if it could not be found.
    *
    * @param customerGtsForecastSid the primary key of the customer gts forecast
    * @return the customer gts forecast
    * @throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsForecast findByPrimaryKey(
        int customerGtsForecastSid)
        throws com.stpl.app.parttwo.NoSuchCustomerGtsForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customerGtsForecastSid the primary key of the customer gts forecast
    * @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CustomerGtsForecast fetchByPrimaryKey(
        int customerGtsForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the customer gts forecasts.
    *
    * @return the customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the customer gts forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of customer gts forecasts
    * @param end the upper bound of the range of customer gts forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CustomerGtsForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the customer gts forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of customer gts forecasts.
    *
    * @return the number of customer gts forecasts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
