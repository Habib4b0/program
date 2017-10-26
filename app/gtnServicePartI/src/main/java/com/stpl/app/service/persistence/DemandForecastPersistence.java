package com.stpl.app.service.persistence;

import com.stpl.app.model.DemandForecast;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DemandForecastPersistenceImpl
 * @see DemandForecastUtil
 * @generated
 */
public interface DemandForecastPersistence extends BasePersistence<DemandForecast> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DemandForecastUtil} to access the demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the demand forecast in the entity cache if it is enabled.
    *
    * @param demandForecast the demand forecast
    */
    public void cacheResult(com.stpl.app.model.DemandForecast demandForecast);

    /**
    * Caches the demand forecasts in the entity cache if it is enabled.
    *
    * @param demandForecasts the demand forecasts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.DemandForecast> demandForecasts);

    /**
    * Creates a new demand forecast with the primary key. Does not add the demand forecast to the database.
    *
    * @param demandForecastSid the primary key for the new demand forecast
    * @return the new demand forecast
    */
    public com.stpl.app.model.DemandForecast create(int demandForecastSid);

    /**
    * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast that was removed
    * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DemandForecast remove(int demandForecastSid)
        throws com.stpl.app.NoSuchDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.DemandForecast updateImpl(
        com.stpl.app.model.DemandForecast demandForecast)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the demand forecast with the primary key or throws a {@link com.stpl.app.NoSuchDemandForecastException} if it could not be found.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast
    * @throws com.stpl.app.NoSuchDemandForecastException if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DemandForecast findByPrimaryKey(
        int demandForecastSid)
        throws com.stpl.app.NoSuchDemandForecastException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param demandForecastSid the primary key of the demand forecast
    * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DemandForecast fetchByPrimaryKey(
        int demandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the demand forecasts.
    *
    * @return the demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DemandForecast> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.DemandForecast> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the demand forecasts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of demand forecasts
    * @param end the upper bound of the range of demand forecasts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DemandForecast> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the demand forecasts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of demand forecasts.
    *
    * @return the number of demand forecasts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
